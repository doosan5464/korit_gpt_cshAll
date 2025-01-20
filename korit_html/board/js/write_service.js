let boardInputDatas = {
    id: 0,
    title: "",
    content: "",
    writer: "",
};

function setInputsEvent() {
    const titleInput = document.querySelector(".main-article > input:nth-of-type(1)");
    const contentInput = document.querySelector(".main-article > textarea");
    const writerInput = document.querySelector(".main-article > input:nth-of-type(2)");
    
    titleInput.onkeyup = handleBoardInputOnChange;
    contentInput.onkeyup = handleBoardInputOnChange;
    writerInput.onkeyup = handleBoardInputOnChange;
}

// 밑에껄로 하다가 강사님꺼 추가
function setButtonEvent() {
    const submitButton = document.querySelector(".write-submit-button");
    submitButton.onclick = handleSubmitOnClick;
}

// 밑에껄로 하다가 강사님꺼 추가
function handleSubmitOnClick() {
    saveBoard();
    clear();
}

function saveBoard() {
    let boardDatas = !!localStorage.getItem("boardDatas") 
    ? JSON.parse(localStorage.getItem("boardDatas"))
    : []; // null이면 빈 배열로 만들어줌

    if(boardDatas.length > 0) {
        boardInputDatas.id = boardDatas[boardDatas.length - 1].id + 1;
    }

    boardDatas = [ // 빈 배열이라도 여기서 추가가 됨
        ...boardDatas,
        boardInputDatas,
    ]
    // 진짜 저장하는 법. 
    // F12하고 application를 가보면 Local storage가 있음
    // localStorage.setItem("text", 문자열 <- JSON으로 넣으면 됨)
    localStorage.setItem("boardDatas", JSON.stringify(boardDatas));

    alert("게시글 작성 완료");
    location.href = "./list.html";
}

// 밑에껄로 하다가 강사님꺼 추가
// 저장하고 남은 내용들을 지우는 추가적인 함수
function clear() {
    const titleInput = document.querySelector(".main-article > input:nth-of-type(1)");
    const contentInput = document.querySelector(".main-article > textarea");
    const writerInput = document.querySelector(".main-article > input:nth-of-type(2)");
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
    // 문제) 저장을 눌렀을 때 boardInputDatas를 출력하라
    // const saveInput = document.querySelector(".main-header > a:nth-of-type(2)");

    // 내가 시도한거 1번
    // saveInput.onclick = console.log(boardInputDatas); 
    // // console.log()는 함수 실행 결과를 반환함, 저장을 누를때 호출이 안되는 이유는 
    // 저장을 눌러봤자 return이 없기때문에 saveInput.onclick에 아무런 값이 저장이 안되서 
    // 코드자체를 읽지 않는다
    
    // 내가 시도한거 2번
    // saveInput.onclick = console.log(boardInputDatas); 
    // saveInput.onclick = () => { // 이렇게 해도 되나 함수가 구분이 안되어 바람직하지 못한 코드
    //     console.log(boardInputDatas);  
    // };
}

setInputsEvent();

// 강사님꺼 추가
setButtonEvent();