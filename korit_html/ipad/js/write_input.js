function loadList() {
    let boardDatas = !!localStorage.getItem("boardDatas") 
    ? JSON.parse(localStorage.getItem("boardDatas"))
    : [];

    const boardList = document.querySelector(".list-board");

    boardList.innerHTML = boardDatas.map(data => `
        <li class="board-box">
            <header class="board-header">
                <h1>${data.title} (작성자 : ${data.writer})</h1>
            </header>
            <main class="board-main">
                <textarea>${data.content}</textarea> 
            </main>
        </li>
        `).join("");
}
loadList();