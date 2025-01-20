import React from 'react';
/*
비동기

Promise(resolve, reject)
then, catch, finally

async, await
try, catch, finally

*/
function App11(props) {

    // setTimeout(() => {
    //     console.log("1");
    //     console.log("2");
        
    //     setTimeout(() => {
    //         console.log("3");
    //     }, 2000)
    // }, 3000); // 3초후에 콘솔출력
    
    // Promise - 약속하다  
    // resolve - 결정하다 / reject - 거부하다
    const isSuccess = false;
    const isSuccess2 = true;

    // 생성하자마자 정의된 함수가 호출. (callback)
    // Promise의 생성자에서 resolve와 reject를 호출
    console.log("1번!!");
    const p1 = new Promise((resolve, reject) => { 
        console.log("Promise1 생성!!");

        if(isSuccess) {
            resolve(); // resolve -> p1.then() 호출
        } else {
            reject();
        }
    });
    p1.then(() => { // then은 미뤄진다 비동기상 resolve와 reject는 then을 예약하고 바로 호출 안함. 
                    // then, catch는 Promise의 객체이자 return도 Promise이기 때문에 다시 then, catch를 쓸 수 있다
        console.log("p1의 then 호출!!");
    }).catch(() => { // then -> resolve / catch -> reject
                     // then이 호출이 되지 않더라도 우선순위의 예약에 관여해서 catch의 우선순위가 밀려난다
        console.log("p1 catch 호출!!");
    })
    // 호출 우선순위 
    // : 일반 함수 = Promise / then = catch


    console.log("2번@@");
    const p2 = new Promise((resolve, reject) => { 
        console.log("Promise2 생성@@");

        if(isSuccess2) {
            resolve();
        } else {
            reject();
        }
    });
    p2.then(() => {
        console.log("p2의 then 호출@@");
    }).then(() => {
        console.log("p2의 2번째 then 호출@@");
    });

    
    console.log("3번##");
    const p3 = new Promise((resolve, reject) => { 
        console.log("Promise3 생성@@");

        const response = {
            status: 200,
            data: {
                username: "aaa",
                password: "1234",
            }
        }

        if(true) {
            resolve({response}); // 원래는 resolve({response : response}) 이거인데 생략가능
                                 // 키 값으로 이러면 값을 다 들고와줌
                                 // resolve(response) → 전달값은 { status: 200, data: {...} }    -  새로운 객체
                                 // resolve({ response }) → 전달값은 { response: { status: 200, data: {...} } }     - 객체 그대로
        } else {
            reject();
        }
    });
    p3.then((r) => {
        console.log(r);
        if(true) {
            throw new Error("오류~~~"); // error를 보내면 그냥 catch로 보내버림
        }
        return {
            response: {
                ...r.response,
                data: {
                    ...r.response.data,
                    name: "김준일",
                    email: "aaa@gmail.com",
                }
            }
        }
    }).then((r) => {
        console.log(r);
    }).catch((error) => {
        console.log(error);
    })


    console.log("4번$$");
    const p4 = new Promise((resolve, reject) => { 
        console.log("Promise4 생성$$");

        const response = {
            status: 400,
            data: {
                errorMassage: "문자열 형식이 맞지 않습니다",
            }
        }

        if(false) {
            resolve();
        } else { // reject의 catch에서 Error처리가 가능하다
            reject(new Error(JSON.stringify({response})));
        }
    });
    p4.catch((error) => {
        console.log(error);
    })


    return (
        <div>
            
        </div>
    );
}

export default App11;