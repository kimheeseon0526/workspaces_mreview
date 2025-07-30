//전역으로 counter 0
// 10 ** 3
let counter = 0;
// function callLocal(n, cb){
//   const add = 10 ** n;
//   counter += add;
//   console.log(`[local] 10^${n} = ${add} -> counter : ${counter}`);
//   if(cb) cb();
// }
// function callAsync(n, cb){
//   const add = 10 ** n;
//   const delay =Math.floor(Math.random() * 1000); // 0~ 999
//
//   setTimeout(() => {
//     counter += add;
//     console.log(`[async] 10^${n} = ${add} -> counter : ${counter}, delay: ${delay}ms`);
//     if(cb) cb();
//   }, delay);
// }
//내가 원하는 순서대로 작동하지않음
// callAsync(0, () =>{
//   callAsync(1, ()=>{
//     callAsync(2, () => {
//       callAsync(3, () => {
//         callAsync(4, () => {
//           callLocal(5,() =>{
//             callLocal(6,() => {
//               callLocal(7,() =>{
//                 callLocal(8,() => {
//                   callLocal(9,() => {
//                     console.log("마지막 호출");
//                   });
//                 });
//               })
//             });
//           });
//         })
//       });
//     });
//   });
// });



// 프로세스의 순서보장

//promise : 미래의 값이 성공(resolve)혹은 실패(reject)될것이라는 약속된 결과를 표현
// const promist = new Promise((resolve, reject) => {
//   //if(성공) resolve(결과);
//   //else reject(에러);
// });
//상태
//pending : 대기중
//fulfilled : 성공 (resolve가 호출된상태)
// rejected: 실패 (reject가 호출된 상태)
// promise
//     .then(result => {/*성공시*/})
//     .then(e => reject(e) /*실패시*/)
//     .finally(() => {/*무조건 실행*/})
// [].sort((a) =>{});

// function asyncTask(){
//   return new Promise();
// }
//
// const asyncTask = () => {
//   return new Promise((resolve) => {
//     setTimeout(() => {
//       resolve("완료");
//     }, 500);
//   });
// };

// const result = asyncTask();
// result
//     .then(msq => {
//       console.log(msg)
//     })
//     .catch(err = > {
//       console.log(err)
// });

//Thenable function

function callLocal(n){
  return new Promise((resolve) => {
    const add = 10 ** n;
    counter += add;
    console.log(`[local] 10^${n} = ${add} -> counter : ${counter}`);
    resolve();
  });
}
function callAsync(n){
  return new Promise((resolve) =>{
    const add = 10 ** n;
    const delay =Math.floor(Math.random() * 1000); // 0~ 999

    setTimeout(() => {
      counter += add;
      console.log(`[async] 10^${n} = ${add} -> counter : ${counter}, delay: ${delay}ms`);
      resolve();
    }, delay);
  });
}

// callAsync(0)
//     .then(() => callAsync(1))
//     .then(() => callAsync(2))
//     .then(() => callAsync(3))
//     .then(() => callAsync(4))
//     .then(() => callLocal(5))
//     .then(() => callLocal(6))
//     .then(() => callLocal(7))
//     .then(() => callLocal(8))
//     .then(() => callLocal(9))
//     .then(() => console.log("마지막호출"));





// (() =>{callAsync(0); }) (); // 즉시실행 함수 형태
// callAsync(1);
// callAsync(2);
// callAsync(3);
// callAsync(4);
// callLocal(5);
// callLocal(6);
// callLocal(7);
// callLocal(8);
// callLocal(9);
//------------------
// $.ajax({
//   success: (data) => {
//     $.ajax({
//       success:(data) =>{
//
//       }
//     })
//   }
// })
//------------------------- jquery스타일의 체이닝 이것도 dsl 방식.
//   $.ajax({
//     success: (data) =>{},
//     error: (data) =>{},
//     finally: (data) =>{}
//   })
//   .done((data) => {
//
//   })
//       .fail(error =>{
//
//       })
//       .always(()=>{
//
//       })
//   .done((data) => {
//
//   })

// -------

//async, await
async function run(){
  await callAsync(0);
  await callAsync(1);
  await callAsync(2);
  await callAsync(3);
  await callAsync(4);
  await callLocal(5);
  await callLocal(6);
  await callLocal(7);
  await callLocal(8);
  console.log("마지막 await 이전");
  await callLocal(9);
  console.log("마지막 await 이후");
}
// run();

// function fetchWithCallback(url = "", callback){
//   fetch(url)
//       .then(response => response.json())
//       .then(data => {
//         console.log("콜백 결과", url)
//         console.log(data)
//         if(callback) callback();
//       })
// }
//비 순차처리
// fetchWithCallback(".../replies/board/1");
// fetchWithCallback(".../replies/board/2");
// fetchWithCallback(".../replies/board/3");
//콜백스타일의  순차처리
// fetchWithCallback("../replies/board/1", () => fetchWithCallback("../replies/board/2", () => fetchWithCallback("../replies/board/3")));

function fetchReplies(bno){
  return fetch(`../replies/board/${bno}`)
      .then(response => response.json())
      .then(data => {
        console.log("콜백 결과", bno)
        console.log(data)
      })
}

// fetchReplies(1)
//     .then (() => fetchReplies(2))
//     .then (() => fetchReplies(3))
//     .then (() => console.log('Promise Call 적용'));

//익명함수 처리
(async() => {
  await fetchReplies(1)
  await fetchReplies(2)
  await fetchReplies(3)
  console.log('IIFE + Await Call 적용')
})(); //IIFE