/*
 * Developed by Razil Minneakhmetov on 10/24/18 10:12 PM.
 * Last modified 10/24/18 10:12 PM.
 * Copyright © 2018. All rights reserved.
 */

package app;

import context.MyApplicationContext;
import lombok.SneakyThrows;
import repositories.CartRepository;
import repositories.UserRepository;


public class Application {

    private static final Integer APP_ID = 6733902;
    private static final String CLIENT_SECRET = "N5eZ94mx68gTDxCYytCx";
    //private static final String REDIRECT_URI = ;

    private static UserRepository repository;

    @SneakyThrows
    public static void main(String[] args) {
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
////        dataSource.setUrl("jdbc:postgresql://localhost:5432/stickershop");
////        dataSource.setUsername("postgres");
////        dataSource.setPassword("r1a2z3i4l5");
////
////        //repository = new UserRepository(dataSource);
////
////        //repository.create(User.builder().name("разиль").hashPassword("пароль").build());
//////
//////        TransportClient transportClient = HttpTransportClient.getInstance();
//////        VkApiClient vk = new VkApiClient(transportClient);
////
////        CartService cartService = new CartService(new CartRepository(dataSource));
////        List<Product> products = cartService.getProductsInCart(193108295l);
////        String string = new Gson().toJson(products);
////        System.out.println(string);
////
//////        UserAuthResponse authResponse = vk.oauth()
//////                .userAuthorizationCodeFlow(APP_ID, CLIENT_SECRET, REDIRECT_URI, code)
//////                .execute();
////
////        //UserActor actor = new UserActor(authResponse.getUserId(), authResponse.getAccessToken());
////
////        TransportClient transportClient = HttpTransportClient.getInstance();
////        VkApiClient vk = new VkApiClient(transportClient);
////        String code = "627637be0845f6db94";
////        UserAuthResponse authResponse = vk.oauth()
////                .userAuthorizationCodeFlow(Constants.APP_ID, Constants.CLIENT_SECRET, Constants.REDIRECT_URI_VK, code)
////                .execute();
////
////        UserActor actor = new UserActor(authResponse.getUserId(), authResponse.getAccessToken());
////        MessagesGetDialogsQuery a = vk.messages().getDialogs(actor);
////
//////        GetDialogsResponse chat = a.execute();
//////        List<Dialog> messages = chat.getItems();
//////        for (int i = 0; i< messages.size(); i++){
//////            System.out.println(messages.get(i).);
//////            System.out.println(messages.get(i).getPhoto50());
//////        }
////
////        System.out.println();


//        Avatar avatar = Avatar.builder().id(123l).URL("jicbsef").build();
//
//        String string = new Gson().toJson(avatar);
        //System.out.println(new Timestamp(System.currentTimeMillis()));
        System.out.println(MyApplicationContext.getMyContext().getAttribute("userRepository") instanceof CartRepository);

        //System.out.println(repository.readOne(193108295l));
        //List<Integer> userIds = chat.getUsers();
        //List<LoginForm> users = new ArrayList<>();


    }
}