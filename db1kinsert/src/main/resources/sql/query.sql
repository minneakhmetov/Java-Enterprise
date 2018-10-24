DO
$do$
BEGIN
   FOR i IN 1..100000 LOOP
       INSERT INTO extra (column_col) values (1234);
   END LOOP;
END
$do$;
DELETE FROM extra;