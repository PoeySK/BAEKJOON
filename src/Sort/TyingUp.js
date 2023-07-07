const { reverse } = require("dns");
const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
let input = fs.readFileSync(filePath).toString().trim().split("\n");

let n = parseInt(input[0]);
let num = [];
let idx = 0;
for (let i = 1; i < input.length; i++) {
  num[idx++] = parseInt(input[i]);
}

num.sort();

let minus = [];
let minusIndex = 0;
let plus = [];
let plusIndex = 0;
for (let i = 0; i < n; i++) {
  if (num[i] > 0) {
    plus[plusIndex++] = num[i];
  } else {
    minus[minusIndex++] = num[i];
  }
}
plus.sort((a, b) => {
  return b - a;
}); // 내림차순 정렬
minus.sort((a, b) => {
  return a - b;
}); // 오름차순 정렬

let rlt = 0;
// plus
for (let i = 1; i < plus.length; i * 2) {
  let tmp = 0;
  if (plus[i - 1] === 1 || plus[i] === 1) {
    tmp = plus[i - 1] + plus[i];
  } else {
    tmp = plus[i - 1] * plus[i];
  }
  rlt += tmp;
  plus.shift();
  plus.shift();
}
if (plus.length == 1) {
  rlt += plus[0];
}

// minus
for (let i = 1; i < minus.length; i * 2) {
  const tmp = minus[i - 1] * minus[i];
  rlt += tmp;
  minus.shift();
  minus.shift();
}
if (minus.length == 1) {
  rlt += minus[0];
}

console.log(rlt);
