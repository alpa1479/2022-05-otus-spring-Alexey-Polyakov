INSERT INTO books_authors(book_id, author_id)
VALUES ((SELECT id FROM books WHERE title = 'The Oblong Box'), (SELECT id FROM authors WHERE name = 'Thomas Artis')),
       ((SELECT id FROM books WHERE title = 'The Oblong Box'), (SELECT id FROM authors WHERE name = 'Rania Martinez')),
       ((SELECT id FROM books WHERE title = 'The Oblong Box'), (SELECT id FROM authors WHERE name = 'Nathanial Snugg')),
       ((SELECT id FROM books WHERE title = 'Skin Deep'), (SELECT id FROM authors WHERE name = 'Rania Martinez')),
       ((SELECT id FROM books WHERE title = 'Summer Catch'), (SELECT id FROM authors WHERE name = 'Nathanial Snugg')),
       ((SELECT id FROM books WHERE title = 'Summer Catch'), (SELECT id FROM authors WHERE name = 'Thomas Artis')),
       ((SELECT id FROM books WHERE title = 'Summer Catch'), (SELECT id FROM authors WHERE name = 'Row Le Sarr')),
       ((SELECT id FROM books WHERE title = 'The Missionaries'),
        (SELECT id FROM authors WHERE name = 'Kathryn Armatys')),
       ((SELECT id FROM books WHERE title = 'The Missionaries'), (SELECT id FROM authors WHERE name = 'Pat Cambridge')),
       ((SELECT id FROM books WHERE title = '9 Star Hotel (Malon 9 Kochavim)'),
        (SELECT id FROM authors WHERE name = 'Row Le Sarr')),
       ((SELECT id FROM books WHERE title = '9 Star Hotel (Malon 9 Kochavim)'),
        (SELECT id FROM authors WHERE name = 'Krystal Ryves')),
       ((SELECT id FROM books WHERE title = 'Street Kings'), (SELECT id FROM authors WHERE name = 'Kathryn Armatys')),
       ((SELECT id FROM books WHERE title = 'Street Kings'), (SELECT id FROM authors WHERE name = 'Krystal Ryves')),
       ((SELECT id FROM books WHERE title = 'Squeeze'), (SELECT id FROM authors WHERE name = 'Pat Cambridge')),
       ((SELECT id FROM books WHERE title = 'Squeeze'), (SELECT id FROM authors WHERE name = 'Nathanial Snugg')),
       ((SELECT id FROM books WHERE title = 'Old San Francisco'), (SELECT id FROM authors WHERE name = 'Thomas Artis')),
       ((SELECT id FROM books WHERE title = 'Old San Francisco'),
        (SELECT id FROM authors WHERE name = 'Krystal Ryves')),
       ((SELECT id FROM books WHERE title = 'Pauly Shore Is Dead'),
        (SELECT id FROM authors WHERE name = 'Rania Martinez')),
       ((SELECT id FROM books WHERE title = 'Pauly Shore Is Dead'),
        (SELECT id FROM authors WHERE name = 'Thomas Artis')),
       ((SELECT id FROM books WHERE title = 'Wayward Bus'), (SELECT id FROM authors WHERE name = 'Nathanial Snugg')),
       ((SELECT id FROM books WHERE title = 'Wayward Bus'), (SELECT id FROM authors WHERE name = 'Thomas Artis')),
       ((SELECT id FROM books WHERE title = 'Brimstone and Treacle'),
        (SELECT id FROM authors WHERE name = 'Kathryn Armatys')),
       ((SELECT id FROM books WHERE title = 'Brimstone and Treacle'),
        (SELECT id FROM authors WHERE name = 'Row Le Sarr')),
       ((SELECT id FROM books WHERE title = 'The Walking Stick'),
        (SELECT id FROM authors WHERE name = 'Krystal Ryves')),
       ((SELECT id FROM books WHERE title = 'The Walking Stick'), (SELECT id FROM authors WHERE name = 'Row Le Sarr')),
       ((SELECT id FROM books WHERE title = 'Eight Below'), (SELECT id FROM authors WHERE name = 'Kathryn Armatys')),
       ((SELECT id FROM books WHERE title = 'Eight Below'), (SELECT id FROM authors WHERE name = 'Krystal Ryves')),
       ((SELECT id FROM books WHERE title = 'Eight Below'), (SELECT id FROM authors WHERE name = 'Pat Cambridge')),
       ((SELECT id FROM books WHERE title = 'Riders of the Purple Sage'),
        (SELECT id FROM authors WHERE name = 'Pat Cambridge')),
       ((SELECT id FROM books WHERE title = 'Harry and Tonto'), (SELECT id FROM authors WHERE name = 'Thomas Artis')),
       ((SELECT id FROM books WHERE title = 'Harry and Tonto'), (SELECT id FROM authors WHERE name = 'Rania Martinez')),
       ((SELECT id FROM books WHERE title = 'Harry and Tonto'), (SELECT id FROM authors WHERE name = 'Krystal Ryves'));