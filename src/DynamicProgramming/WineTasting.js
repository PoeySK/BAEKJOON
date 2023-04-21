let fs = require('fs');
const [n, ...wine] = fs.readFileSync('/dev/stdin').toString().trim().split('\n').map(v => +v);
// const [n, ...wine] = fs.readFileSync('../../example.txt').toString().trim().split('\n').map(v => +v);

switch(n) {
    case 1:
        console.log(wine[0]);
        break;
    case 2:
        console.log(wine[0] + wine[1]);
        break;
    default:
        let solution = new Array(n).fill(0);
        solution[1] = wine[0];
        solution[2] = wine[0] + wine[1];
        for(let i = 3; i<=n; i++) {
            /*
                      현시점
             1. X   O   O
             2. O   X   O
             3. O   O   X
            */
            solution[i] = Math.max(solution[i-3] + wine[i-2] + wine[i-1], solution[i-2] + wine[i-1], solution[i-1]);
        }
        console.log(solution[n]);
        break;
}