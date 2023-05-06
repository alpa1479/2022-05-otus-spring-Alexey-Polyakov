import {useEffect, useState} from 'react';
import {Link} from 'react-router-dom';

import {deleteBook, getBooks} from "../../services/api/booksService";
import {deleteComments} from "../../services/api/commentsService";

function BooksPage() {
    const [books, setBooks] = useState([]);

    useEffect(() => {
        const fetchBooks = async () => {
            try {
                const response = await getBooks();
                setBooks(response.data);
            } catch (error) {
                console.error(error);
            }
        };

        fetchBooks();
    }, []);

    const handleDeleteComments = async (id) => {
        try {
            await deleteComments(id);
            setBooks(prevBooks =>
                prevBooks.map(book =>
                    book.id === id ? {...book, comments: []} : book
                )
            );
        } catch (error) {
            console.error(error);
        }
    };

    const handleDeleteBook = async (id) => {
        try {
            await deleteBook(id);
            setBooks(prevBooks => prevBooks.filter(book => book.id !== id));
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
                    <th>Identifier</th>
                    <th>Title</th>
                    <th>Genre</th>
                    <th>Authors</th>
                    <th>Comments</th>
                    <th>Actions</th>
                </tr>
                </thead>
                <tbody>
                {books.map(book => (
                    <tr key={book.id}>
                        <td>{book.id}</td>
                        <td>{book.title}</td>
                        <td>{book.genre.name}</td>
                        <td>
                            {book.authors.map(author => (
                                <div key={author.id}>
                                    <p>{author.name}</p>
                                </div>
                            ))}
                        </td>
                        <td>
                            {book.comments.map(comment => (
                                <div key={comment.id}>
                                    <p>{comment.text}</p>
                                </div>
                            ))}
                        </td>
                        <td>
                            <div className="btn-group-vertical">
                                <Link className="btn btn-primary btn-sm" to={`/update-book?id=${book.id}`}>Edit</Link>
                                <button className="btn btn-warning btn-sm mt-1"
                                        onClick={() => handleDeleteComments(book.id)}>Delete comments
                                </button>
                                <button className="btn btn-danger btn-sm mt-1"
                                        onClick={() => handleDeleteBook(book.id)}>Delete
                                </button>
                            </div>
                        </td>
                    </tr>
                ))}
                </tbody>
            </table>

            <div className="row">
                <div className="col-2">
                    <Link className="btn btn-success btn-block" to="/add-book">Add a book</Link>
                </div>
            </div>
        </div>
    );
}

export default BooksPage;