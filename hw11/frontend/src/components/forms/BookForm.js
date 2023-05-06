import {useNavigate} from 'react-router-dom';
import {useState} from 'react';

import {addComment} from "../services/api/commentsService";

function BookForm({book, genres, authors, handleSubmit}) {
    const [bookToSave, setBookToSave] = useState(book);
    const [commentToSave, setCommentToSave] = useState({text: ''});
    const navigate = useNavigate();

    const handleTitleChange = (event) => {
        const newBook = {...bookToSave, title: event.target.value};
        setBookToSave(newBook);
    };

    const handleAuthorChange = (event) => {
        const newBook = {
            ...bookToSave,
            authorIds: Array.from(event.target.selectedOptions).map((option) => option.value)
        };
        setBookToSave(newBook);
    };

    const handleGenreChange = (event) => {
        const newBook = {...bookToSave, genreId: event.target.value};
        setBookToSave(newBook);
    };

    const handleCommentChange = (event) => {
        const newComment = {...commentToSave, text: event.target.value};
        setCommentToSave(newComment);
    };

    const handleCommentSubmit = (event, bookId, text) => {
        event.preventDefault();
        addComment({bookId: bookId, text: text})
            .then(() => navigate('/'))
            .catch(error => console.error(error));
    };

    const handleCancel = () => {
        navigate('/');
    };

    return (
        <div className="container mt-5">
            <form onSubmit={(event) => handleSubmit(event, bookToSave)}>
                <h3 className="mb-3">Book details:</h3>

                <div className="form-group row">
                    <label htmlFor="book-title-input" className="col-sm-2 col-form-label">Title:</label>
                    <div className="col-sm-10">
                        <input id="book-title-input" name="title" type="text" className="form-control"
                               value={bookToSave.title}
                               onChange={handleTitleChange} required/>
                    </div>
                </div>

                <div className="form-group row">
                    <label htmlFor="authorIds" className="col-sm-2 col-form-label">Select authors:</label>
                    <div className="col-sm-10">
                        <select id="authorIds" name="authorIds" className="form-control" multiple required
                                value={bookToSave.authorIds} onChange={handleAuthorChange}>
                            <option value="" disabled hidden>Select authors</option>
                            {authors.map(author => (
                                <option key={author.id} value={author.id}>{author.name}</option>
                            ))}
                        </select>
                    </div>
                </div>

                <div className="form-group row">
                    <label htmlFor="genreId" className="col-sm-2 col-form-label">Select genre:</label>
                    <div className="col-sm-10">
                        <select id="genreId" name="genreId" className="form-control" required
                                value={bookToSave.genreId} onChange={handleGenreChange}>
                            <option value="" disabled hidden>Select genre</option>
                            {genres.map(genre => (
                                <option key={genre.id} value={genre.id}>{genre.name}</option>
                            ))}
                        </select>
                    </div>
                </div>

                <div className="form-group row">
                    <div className="col-sm-2"></div>
                    <div className="col-sm-10">
                        <button type="submit" className="btn btn-primary mr-1">{bookToSave.id ? 'Update' : 'Create'}</button>
                        <button className="btn btn-secondary" onClick={handleCancel}>Cancel</button>
                    </div>
                </div>
            </form>

            {book.id && (
                <form onSubmit={(event) => handleCommentSubmit(event, bookToSave.id, commentToSave.text)}>
                    <h3>Add new comment:</h3>
                    <div className="form-group row">
                        <label htmlFor="book-new-comment-input" className="col-sm-2 col-form-label">Text:</label>
                        <div className="col-sm-10">
                            <input type="hidden" name="bookId" value={bookToSave.id}/>
                            <input id="book-new-comment-input" name="text" type="text" value={commentToSave.text}
                                   className="form-control" onChange={handleCommentChange} required/>
                        </div>
                    </div>

                    <div className="form-group row">
                        <div className="col-sm-10 offset-sm-2">
                            <button type="submit" className="btn btn-primary">Add</button>
                        </div>
                    </div>
                </form>
            )}
        </div>
    );
}

export default BookForm;