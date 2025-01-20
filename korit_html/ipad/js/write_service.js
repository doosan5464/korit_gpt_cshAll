let boardInputDatas = {
    id: 0,
    title: "",
    content: "",
    writer: "",
    password: "",
};

function setInputsEvent() {
    const titleInput = document.querySelector(".write-title");
    const contentInput = document.querySelector(".write-content");
    const writerInput = document.querySelector(".write-writer");
    
    titleInput.onkeyup = handleBoardInputOnChange;
    contentInput.onkeyup = handleBoardInputOnChange;
    writerInput.onkeyup = handleBoardInputOnChange;
}

function setButtonEvent() {
    const submitButton = document.querySelector(".write-submit-button");
    submitButton.onclick = handleSubmitOnClick;
}

function handleSubmitOnClick() {
    saveBoard();
    clear();
}

function saveBoard() {
    let boardDatas = !!localStorage.getItem("boardDatas") 
    ? JSON.parse(localStorage.getItem("boardDatas"))
    : []; 

    if(boardDatas.length > 0) {
        boardInputDatas.id = boardDatas[boardDatas.length - 1].id + 1;
    }

    boardDatas = [ 
        ...boardDatas,
        boardInputDatas,
    ]

    localStorage.setItem("boardDatas", JSON.stringify(boardDatas));
    alert("게시글 작성 완료");
    location.href = "./list.html";
}

function clear() {
    const titleInput = document.querySelector(".write-title");
    const contentInput = document.querySelector(".write-content");
    const writerInput = document.querySelector(".write-writer");
    const inputs = [titleInput, contentInput, writerInput];
    // 공백으로 초기화
    inputs.forEach(input => input.value = "");
    boardInputDatas = {
        title: "",
        content: "",
        writer: "",
    };
} 

function handleBoardInputOnChange(e) {
    boardInputDatas = { /*입력할 때 마다 객체에 값이 들어감 - 값을 뒤덮으니까*/
        ...boardInputDatas,
        [e.target.name]:e.target.value,
    }
}
setInputsEvent();
setButtonEvent();