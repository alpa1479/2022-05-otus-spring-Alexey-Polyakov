import {useNavigate} from 'react-router-dom';
import {useState} from 'react';
import {create} from "../services/api/restService";

function BookForm({isUpdate, book, genres, authors, handleSubmit}) {
    const navigate = useNavigate();

    const [bookToSave, setBookToSave] = useState({title: book?.title, authors: book?.authors, genre: book?.genre});
    const [commentToSave, setCommentToSave] = useState({text: ''});

    const handleTitleChange = (event) => {
        const newBook = {...bookToSave, title: event.target.value};
        setBookToSave(newBook);
    };

    const handleAuthorChange = (event) => {
        const newBook = {
            ...bookToSave,
            authors: Array.from(event.target.selectedOptions).map((option) => option.value)
        };
        setBookToSave(newBook);
    };

    const handleGenreChange = (event) => {
        const newBook = {...bookToSave, genre: event.target.value};
        setBookToSave(newBook);
    };

    const handleCommentChange = (event) => {
        const newComment = {...commentToSave, text: event.target.value};
        setCommentToSave(newComment);
    };

    const handleCommentSubmit = (event, linkToBook, text) => {
        event.preventDefault();
        create(linkToBook, {text: text})
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
                    <label htmlFor="authors" className="col-sm-2 col-form-label">Select authors:</label>
                    <div className="col-sm-10">
                        <select id="authors" name="authors" className="form-control" multiple required
                                value={bookToSave.authors} onChange={handleAuthorChange}>
                            <option value="" disabled hidden>Select authors</option>
                            {authors.map(author => (
                                <option key={author._links.self.href}
                                        value={author._links.self.href}>{author.name}</option>
                            ))}
                        </select>
                    </div>
                </div>

                <div className="form-group row">
                    <label htmlFor="genre" className="col-sm-2 col-form-label">Select genre:</label>
                    <div className="col-sm-10">
                        <select id="genre" name="genre" className="form-control" required
                                value={bookToSave.genre} onChange={handleGenreChange}>
                            <option value="" disabled hidden>Select genre</option>
                            {genres.map(genre => (
                                <option key={genre._links.self.href}
                                        value={genre._links.self.href}>{genre.name}</option>
                            ))}
                        </select>
                    </div>
                </div>

                <div className="form-group row">
                    <div className="col-sm-2"></div>
                    <div className="col-sm-10">
                        <button type="submit" className="btn btn-primary mr-1">{isUpdate ? 'Update' : 'Create'}</button>
                        <button className="btn btn-secondary" onClick={handleCancel}>Cancel</button>
                    </div>
                </div>
            </form>

            {book.title && (
                <form onSubmit={(event) => handleCommentSubmit(event, book._links.comments.href, commentToSave.text)}>
                    <h3>Add new comment:</h3>
                    <div className="form-group row">
                        <label htmlFor="book-new-comment-input" className="col-sm-2 col-form-label">Text:</label>
                        <div className="col-sm-10">
                            <input type="hidden" name="book" value={book._links.comments.href}/>
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