import axios from 'axios';
import React from 'react';

function App14(props) {



    const handleRequestOnClick = async () => {
        let response = null;
        try { // axios : intelliJ랑 연동되는듯
            response = await axios.get("http://localhost:8080/servlet_study_war/api/user"); // return이 Promise
            console.log(response);
            console.log(response.data);
        } catch (error) {
            console.error(error);
        }
    }

    /*
    BookRestServlet(/api/book) 를 해라
    get요청
    bookId,
    bookName,
    author,
    publisher,
    category,
    imgUrl,
    */

    const handleBookRequestOnClick = async () => {
        let response = null;
        try {
            response = await axios.get("http://localhost:8080/servlet_study_war/api/book"); // return이 Promise
            console.log(response);
            console.log(response.data);
        } catch (error) {
            console.error(error);
        }
    }

    return (
        <div>
            <button onClick={handleRequestOnClick}>유저요청</button>        
            <button onClick={handleBookRequestOnClick}>도서요청</button>        
        </div>
    );
}

export default App14;