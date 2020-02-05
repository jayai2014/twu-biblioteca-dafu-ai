INSERT INTO book
VALUES (11, 'The Pragmatic Programmar');

INSERT INTO member VALUES (43, 'Dafu Ai');

INSERT INTO checkout_item
VALUES (43, 11, NULL);

-- Check result
SELECT m.name FROM checkout_item AS c
INNER JOIN book b ON c.book_id = b.id
INNER JOIN member m ON c.member_id = m.id
WHERE b.title = 'The Pragmatic Programmer';