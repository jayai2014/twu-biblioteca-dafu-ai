SELECT COUNT(*) AS no_people_checkout_nothing FROM (
	SELECT (COUNT(book_id) +  COUNT(movie_id)) as checkout_count
	FROM member m 
	LEFT JOIN checkout_item c  ON m.id = c.member_id 
	GROUP BY m.id HAVING checkout_count = 0);
