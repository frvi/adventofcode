import * as fs from 'fs';
import * as readline from 'readline';

describe('day 01', function () {
  test('sample 01', async function () {

    const rl = readline.createInterface({
      input: fs.createReadStream('test/resources/day01.sample'),
      crlfDelay: Infinity
    });

    const numbers : number[] = [];

    rl.on('line', (line) => {
      if (line === "") {
        numbers.push(0);
      } else {
        numbers.push(parseInt(line, 10));
      }
    });

    await new Promise((res) => rl.once('close', res));

    // sum each group of numbers in sum
    let i = 0;
    const sums : number[] = [0];
    numbers.forEach((num, index) => {
      if (num != 0) {
        sums[i] += num;
      } else {
        i++;
        sums[i] = 0;
      }
    })

    console.log(Math.max(...sums));

  });

  test('part 01', async function () {

    const rl = readline.createInterface({
      input: fs.createReadStream('test/resources/day01'),
      crlfDelay: Infinity
    });

    const numbers : number[] = [];

    rl.on('line', (line) => {
      if (line === "") {
        numbers.push(0);
      } else {
        numbers.push(parseInt(line, 10));
      }
    });

    await new Promise((res) => rl.once('close', res));

    // sum each group of numbers in sum
    let i = 0;
    const sums : number[] = [0];
    numbers.forEach((num, index) => {
      if (num != 0) {
        sums[i] += num;
      } else {
        i++;
        sums[i] = 0;
      }
    })

    console.log(Math.max(...sums));

  });

  test('sample 02', async function () {

    const rl = readline.createInterface({
      input: fs.createReadStream('test/resources/day01.sample'),
      crlfDelay: Infinity
    });

    const numbers : number[] = [];

    rl.on('line', (line) => {
      if (line === "") {
        numbers.push(0);
      } else {
        numbers.push(parseInt(line, 10));
      }
    });

    await new Promise((res) => rl.once('close', res));

    let i = 0;
    const sums : number[] = [0];
    numbers.forEach((num, index) => {
      if (num != 0) {
        sums[i] += num;
      } else {
        i++;
        sums[i] = 0;
      }
    })

    const result = sums.sort((a, b) => { return a < b ? -1 : 1})
      .splice(sums.length-3)
      .reduce((acc, curr) => acc + curr, 0);

    console.log(result);

  });

  test('part 02', async function () {

    const rl = readline.createInterface({
      input: fs.createReadStream('test/resources/day01'),
      crlfDelay: Infinity
    });

    const numbers : number[] = [];

    rl.on('line', (line) => {
      if (line === "") {
        numbers.push(0);
      } else {
        numbers.push(parseInt(line, 10));
      }
    });

    await new Promise((res) => rl.once('close', res));

    let i = 0;
    const sums : number[] = [0];
    numbers.forEach((num, index) => {
      if (num != 0) {
        sums[i] += num;
      } else {
        i++;
        sums[i] = 0;
      }
    })

    const result = sums.sort((a, b) => { return a < b ? -1 : 1})
      .splice(sums.length-3)
      .reduce((acc, curr) => acc + curr, 0);

    console.log(result);

  });
});