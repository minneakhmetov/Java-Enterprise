bodyParser = require('body-parser').json();
var jsonfile = require('jsonfile');
var http = require('http');
var Cookie = require('cookies');
module.exports = function (app, fs) {
    app.post('/reg', bodyParser, function (request, response) {
        // вытаскиваю тело в формате JSON
        var body = request.body;
        console.log(body);
        var path = './users/' + body.user + '.json';
        // записываю его в файл
        try {
            var obj = jsonfile.readFileSync(path);
            response.redirect('html/alreadyRegistered.html');
        } catch (err) {
            jsonfile.writeFileSync(path, body);
            response.redirect('html/success.html');
        }
        response.end();
    });
    app.get('/', function (request, response){
        response.redirect('html/home.html');
    });
    app.post('/login', bodyParser, function (request, response) {
        app.set('view engine', 'ejs');
        var body = request.body;
        var cookies = new Cookie(request, response);
        console.log(body);
        var path = './users/' + body.user + '.json';
        try {
            var obj = jsonfile.readFileSync(path);
            if (obj.pass === body.pass) {
                cookies.set('user', body.user);
                cookies.set('pass', body.pass);
                response.render('user', {fio: obj.first + ' ' + obj.last, user: obj.user});
            } else
                response.redirect('html/userNotFound.html');
        } catch (err) {
            response.redirect('html/userNotFound.html')
        }
        response.end();

    });
    app.post('/delete', bodyParser, function (request, response) {
        var cookies = new Cookie(request, response);
        var path = './users/' + cookies.get('user') + '.json';
        fs.unlinkSync(path);
        cookies.set('user', '');
        cookies.set('pass', '');
        response.redirect('html/userDeleted.html');
        response.end();
    });

    app.post('/logout', bodyParser, function (request, response) {
        var cookies = new Cookie(request, response);
        cookies.set('user', '');
        cookies.set('pass', '');
    });
    app.post('/chat', bodyParser, function (request, response) {
        var cookies = new Cookie(request, response);
        var body = request.body;
        var path = './chats/';

        fs.readdir(path, function (err, items) {
            var flag = false;
            var file = '';
            for (var i = 0; i < items.length; i++){
                if(items[i].toString().indexOf(cookies.get('user')) >=0 & items[i].toString().indexOf(body.user)>=0){
                    file = items[i].toString();
                    flag |= true;
                    break;
                }
            }
            try{
                fs.readFileSync('./users/' + body.user + '.json');
                if (!flag) {
                    file = path + cookies.get('user') + '-' + body.user + '.txt';
                    fs.writeFileSync(file, '');
                    cookies.set('chat', file);
                    response.redirect('html/page.html');
                } else {
                    cookies.set('chat', path + file);
                    response.redirect('html/page.html');
                }
            } catch (e) {
                response.redirect('html/userNotFound.html')
            }

        });

    });

    app.post('/message', bodyParser, function (request, response) {
        var cookies = new Cookie(request, response);
        var path = cookies.get('chat');
        var body = request.body;
        fs.appendFileSync(path, cookies.get('user') + ': ' + body.message + '|');
        response.redirect('html/page.html');
    });

    app.get('/getMessage', function (request, response) {
        var cookies = new Cookie(request, response);
        var path = cookies.get('chat');
        var value = fs.readFileSync(path, 'utf-8');
        console.log(value);
        response.send(value);
    });


    app.post('/search', bodyParser, function (request, response) {
        var cookie = new Cookie(request, response);
        var body = request.body;
        var path = './users/';
        //console.log(body.search)

        fs.readdir(path, function (err, items) {
            var result = [];
            for (var i = 0; i < items.length; i++) {
                if ((items[i].toString().indexOf(body.search) >= 0)) {
                    //console.log(items[i]);
                    var obj = jsonfile.readFileSync(path + items[i].toString());
                    //console.log(obj);
                    result[result.length] = obj;
                }
            }
            var searchPath = './search/' + body.search + '.json';
            cookie.set('search', body.search);
            jsonfile.writeFileSync(searchPath, result);
            response.redirect('html/search.html');
        });
    });
    app.get('/searchUsers', function (request, response) {
        var cookie = new Cookie(request,response);
        var search = cookie.get('search');
        var searchPath = './search/' + search + '.json';
        var result = jsonfile.readFileSync(searchPath);
        console.log(result);
        response.setHeader('Content-Type', 'application/json');
        response.send(JSON.stringify(result));
        fs.unlinkSync(searchPath);
    });
};
