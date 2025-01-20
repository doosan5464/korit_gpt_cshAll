import "./styles/app8.css";
import React, { useState } from 'react';
import BookRegister from './components/book/BookRegister/BookRegister';
import BookSearch from './components/book/BookSearch/BookSearch';

function App8(props) {

    const [ bookList, setBookList ] = useState([]);

    return (
        <div className="container">
            <BookRegister bookList={bookList} setBookList={setBookList}/>
            <BookSearch bookList={bookList}/>
        </div>
    );
}


export default App8;