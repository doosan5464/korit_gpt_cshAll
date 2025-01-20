import React, { useState } from 'react';
import "./style.css"

function BookSearchItems({setBookTableList, bookList}) { // bookList는 부모-부모 : 그래서 나중에 전역객체로 만듦

    const [ searchValue, setSearchValue ] = useState({
        select: "bookName",
        text: "",
    });

    const searchOptions = [
        {
            id: 1,
            label: "도서명",
            value: "bookName",
        },
        {
            id: 2,
            label: "저자명",
            value: "author",
        },
        {
            id: 3,
            label: "출판사",
            value: "publisher",
        },
    ];

    const handleSearchValueOnChange = (e) => {
        setSearchValue({
            ...searchValue,
            [e.target.name]: e.target.value,
        });
    }

    const handleSearchButtonOnClick = () => {

        if(!searchValue.text.trim()) {
            setBookTableList(bookList);
            return;
        }

        console.log(searchValue.select);
        const foundBooks = bookList.filter(book => book[searchValue.select].includes(searchValue.text));
        setBookTableList(foundBooks);
    }



    return (
        <div className='search-items'>
            <select name="select" value={searchValue.select} onChange={handleSearchValueOnChange}>
                {
                    searchOptions.map(option => <option key={option.id} value={option.value}>{option.label}</option>)
                }
            </select>
            <input type="text" name='text' value={searchValue.text} onChange={handleSearchValueOnChange} />
            <button onClick={handleSearchButtonOnClick}>검색</button>
        </div>
    );
}

export default BookSearchItems;