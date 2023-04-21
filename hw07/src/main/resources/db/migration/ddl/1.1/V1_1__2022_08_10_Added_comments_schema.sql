DROP TABLE IF EXISTS comments;

CREATE TABLE comments
(
    id      BIGSERIAL PRIMARY KEY,
    book_id BIGINT REFERENCES BOOKS(id) ON DELETE CASCADE,
    text    TEXT CHECK(length(text) <= 4096) NOT NULL
);