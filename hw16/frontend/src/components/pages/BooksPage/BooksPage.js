import {useEffect, useState} from 'react';
import {Link} from 'react-router-dom';

import {getBooks} from "../../services/api/booksService";
import {remove} from "../../services/api/restService";

function BooksPage() {
    const [books, setBooks] = useState([]);
    const [bookLinks, setBookLinks] = useState([]);

    useEffect(() => {
        const fetchBooks = async () => {
            try {
                const response = await getBooks();
                setBooks(response.data._embedded.books);
                setBookLinks(response.data._links);
            } catch (error) {
                console.error(error);
            }
        };

        fetchBooks();
    }, []);

    const handleDeleteComments = async (book) => {
        try {
            await remove(book._links.comments.href);
            setBooks(prevBooks =>
                prevBooks.map(prevBook =>
                    prevBook.id === book.id ? {...prevBook, comments: []} : prevBook
                )
            );
        } catch (error) {
            console.error(error);
        }
    };

    const handleDeleteBook = async (book) => {
        try {
            await remove(book._links.self.href);
            setBooks(prevBooks => prevBooks.filter(prevBook => prevBook.id !== book.id));
        } catch (error) {
            console.error(error);
        }
    };

    return (
        <div className="container mt-5">
            <h3 className="mb-3 text-left">Books in library</h3>

            <table className="table table-bordered table-hover">
                <thead className="thead-light">
                <tr>
                    <th>Title</th>
                    <th>Genre</th>
                    <th>Authors</th>
                    <th>Comments</th>
                    <th>Actions</th>
                </tr>
                </thead>
                <tbody>
                {books.map(book => (
                    <tr key={book._links.self.href}>
                        <td>{book.title}</td>
                        <td>{book.genre.name}</td>
                        <td>
                            {book.authors.map(author => (
                                <div key={author.name}>
                                    <p>{author.name}</p>
                                </div>
                            ))}
                        </td>
                        <td>
                            {book?.comments?.map(comment => (
                                <div key={comment.text}>
                                    <p>{comment.text}</p>
                                </div>
                            ))}
                        </td>
                        <td>
                            <div className="btn-group-vertical">
                                <Link className="btn btn-primary btn-sm" to="/update-book" state={book}>Edit</Link>
                                <button className="btn btn-warning btn-sm mt-1"
                                        onClick={() => handleDeleteComments(book)}>Delete comments
                                </button>
                                <button className="btn btn-danger btn-sm mt-1"
                                        onClick={() => handleDeleteBook(book)}>Delete
                                </button>
                            </div>
                        </td>
                    </tr>
                ))}
                </tbody>
            </table>

            <div className="row">
                <div className="col-2">
                    <Link className="btn btn-success btn-block" to="/add-book" state={bookLinks}>Add a book</Link>
                </div>
            </div>
        </div>
    );
}

export default BooksPage;