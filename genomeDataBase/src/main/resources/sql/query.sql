SELECT ((SELECT count(*)
        FROM (SELECT * FROM first_genome_two
              INTERSECT
              SELECT * FROM second_genome_two) AS intersect_genome_two) :: float /
       (SELECT count(*)
        FROM (SELECT * FROM first_genome_two
              UNION
              SELECT * FROM second_genome_two) AS intersect_genome_five) :: float) AS percent_two,
       ((SELECT count(*)
        FROM (SELECT * FROM first_genome_five
              INTERSECT
              SELECT * FROM second_genome_five) AS intersect_genome_nine) :: float /
       (SELECT count(*)
        FROM (SELECT * FROM first_genome_five
              UNION
              SELECT * FROM second_genome_five) AS union_genome_two) :: float) AS percent_five,
       ((SELECT count(*)
        FROM (SELECT * FROM first_genome_nine
              INTERSECT
              SELECT * FROM second_genome_nine) AS union_genome_five) :: float /
       (SELECT count(*)
        FROM (SELECT * FROM first_genome_nine
              UNION
              SELECT * FROM second_genome_nine) AS union_genome_nine) :: float) AS percent_nine,
       (SELECT count(*) FROM (SELECT two FROM first_genome_two
                              GROUP BY two) AS foo) AS count_first_genome_two,
       (SELECT count(*) FROM (SELECT five FROM first_genome_five
                              GROUP BY five) AS foo) AS count_first_genome_five,
       (SELECT count(*) FROM (SELECT nine FROM first_genome_nine
                              GROUP BY nine) AS foo) AS count_first_genome_nine,
       (SELECT count(*) FROM (SELECT two FROM second_genome_two
                              GROUP BY two) AS foo) AS count_second_genome_two,
       (SELECT count(*) FROM (SELECT five FROM second_genome_five
                              GROUP BY five) AS foo) AS count_second_genome_five,
       (SELECT count(*) FROM (SELECT nine FROM second_genome_nine
                              GROUP BY nine) AS foo) AS count_second_genome_nine,
       ((SELECT count(*)
        FROM (SELECT * FROM first_genome_two
              INTERSECT
              SELECT * FROM second_genome_two) AS Foo) :: float) AS intersect_genome_two,
       ((SELECT count(*)
        FROM (SELECT * FROM first_genome_five
              INTERSECT
              SELECT * FROM second_genome_five) AS Foo) :: float) AS intersect_genome_five,
       ((SELECT count(*)
        FROM (SELECT * FROM first_genome_nine
              INTERSECT
              SELECT * FROM second_genome_nine) AS Foo) :: float) AS intersect_genome_nine,
       ((SELECT count(*)
        FROM (SELECT * FROM first_genome_two
              UNION
              SELECT * FROM second_genome_two) AS Foo) :: float) AS union_genome_two,
       ((SELECT count(*)
        FROM (SELECT * FROM first_genome_five
              UNION
              SELECT * FROM second_genome_five) AS Foo) :: float) AS union_genome_five,
       ((SELECT count(*)
        FROM (SELECT * FROM first_genome_nine
              UNION
              SELECT * FROM second_genome_nine) AS Foo) :: float) AS union_genome_nine;







