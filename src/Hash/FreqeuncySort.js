const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
let input = fs.readFileSync(filePath).toString().trim().split("\n");

const nm = input[0].split(" ");
let n = nm[0];
let c = nm[1];

const num = input[1].split(" ");
let m = new Map();
for (let i = 0; i < n; i++) {
  const k = num[i];
  if (m.has(k)) {
    const [value, idx] = m.get(k);
    m.set(k, [value + 1, idx]);
  } else {
    m.set(k, [1, i]); // (key, [value, index])
  }
}

// key, value 추출
rlt = [];
for (const [key, value] of m) {
  rlt.push([key, value]);
}

// value와 index에 맞게 정렬
rlt.sort((a, b) => {
  if (a[1][0] === b[1][0]) {
    return a[1][1] - b[1][1]; // index는 오름차순
  } else {
    return b[1][0] - a[1][0]; // value는 내림차순
  }
});

for (let i = 0; i < rlt.length; i++) {
  // rlt[key][[value, index]] -> key => rlt[i], value => rlt[i][1]
  for (let j = 0; j < rlt[i][1][0]; j++) {
    process.stdout.write(rlt[i][0] + " ");
  }
}
