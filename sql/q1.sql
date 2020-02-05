SELECT m.name FROM checkout_item AS c
INNER JOIN book b ON c.book_id = b.id
INNER JOIN member m ON c.member_id = m.id
WHERE b.title = 'The Hobbit';