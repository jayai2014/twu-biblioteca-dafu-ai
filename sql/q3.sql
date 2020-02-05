SELECT b.title, COUNT(c.book_id) AS checkout_count
FROM book b
LEFT JOIN checkout_item c  ON b.id = c.book_id 
GROUP BY b.id HAVING checkout_count = 0
UNION ALL
SELECT m.title, COUNT(c.movie_id) AS checkout_count
FROM movie m
LEFT JOIN checkout_item c  ON m.id = c.movie_id 
GROUP BY m.id HAVING checkout_count = 0;

