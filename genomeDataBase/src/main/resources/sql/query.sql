WITH inter_two as (SELECT count(*)
                   FROM (SELECT * FROM first_genome_two
                         INTERSECT
                         SELECT * FROM second_genome_two) AS Foo),

     inter_five as (SELECT count(*)
                    FROM (SELECT * FROM first_genome_five
                          INTERSECT
                          SELECT * FROM second_genome_five) AS Foo),
     inter_nine as (SELECT count(*)
                    FROM (SELECT * FROM first_genome_nine
                          INTERSECT
                          SELECT * FROM second_genome_nine) AS Foo),
     union_two AS (SELECT count(*)
                   FROM (SELECT * FROM first_genome_two
                         UNION
                         SELECT * FROM second_genome_two) AS Foo),
     union_five as (SELECT count(*)
                    FROM (SELECT * FROM first_genome_five
                          UNION
                          SELECT * FROM second_genome_five) AS Foo),
     union_nine as (SELECT count(*)
                    FROM (SELECT * FROM first_genome_nine
                          UNION
                          SELECT * FROM second_genome_nine) AS Foo),
     count_first_genome_two_cte as (SELECT count(*) FROM (SELECT two FROM first_genome_two
                                                          GROUP BY two) AS foo),
     count_first_genome_five_cte as (SELECT count(*) FROM (SELECT five FROM first_genome_five
                                                           GROUP BY five) AS foo),
     count_first_genome_nine_cte as (SELECT count(*) FROM (SELECT nine FROM first_genome_nine
                                                           GROUP BY nine) AS foo),
     count_second_genome_two_cte as (SELECT count(*) FROM (SELECT two FROM second_genome_two
                                                           GROUP BY two) AS foo),
     count_second_genome_five_cte as (SELECT count(*) FROM (SELECT five FROM second_genome_five
                                                            GROUP BY five) AS foo),
     count_second_genome_nine_cte as (SELECT count(*) FROM (SELECT nine FROM second_genome_nine
                                                            GROUP BY nine) AS foo)
SELECT (select count from inter_two) as intersect_genome_two, (select count from inter_five) as intersect_genome_five,

       (select count from inter_nine) as intersect_genome_nine, (select count from union_two) as union_genome_two,
       (select count from union_five) as union_genome_five, (select count from union_nine) as union_genome_nine,
       (select (select count from inter_two)/(select count from union_two) :: float as percent_two), (select (select count from inter_five)/(select count from union_five) ::float as percent_five),
       (select (select count from inter_nine)/(select count from union_nine) :: float as percent_nine), (select count from count_first_genome_two_cte) as count_first_genome_two,
       (select count from count_first_genome_five_cte) as count_first_genome_five, (SELECT count from count_first_genome_nine_cte) as count_first_genome_nine,
       (select count from count_second_genome_two_cte) as count_second_genome_two, (select count from count_second_genome_five_cte) as count_second_genome_five,
       (select count from count_second_genome_nine_cte) as count_second_genome_nine;