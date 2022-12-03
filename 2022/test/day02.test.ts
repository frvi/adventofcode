import * as fs from 'fs';
import * as readline from 'readline';

describe('day 02, part 01', function () {
  // A - rock
  // B - paper
  // C - scissors
  // X - rock (1p)
  // Y - paper (2p)
  // Z - scissors (3p)
  // wins (6p)
  // AY = 2 + 6 = 8
  // BZ = 3 + 6 = 9
  // CX = 1 + 6 = 7
  // draws (3p)
  // AX = 1 + 3 = 4
  // BY = 2 + 3 = 5
  // CZ = 3 + 3 = 6
  // losses (0p)
  // AZ = 3 + 0 = 3
  // BX = 1 + 0 = 1
  // CY = 2 + 0 = 2
  const outcome: { [key: string]: number } = {
    AY: 8,
    BZ: 9,
    CX: 7,
    AX: 4,
    BY: 5,
    CZ: 6,
    AZ: 3,
    BX: 1,
    CY: 2
  };

  test('sample', async function () {
    const rl = readline.createInterface({
      input: fs.createReadStream('test/resources/day02.sample'),
      crlfDelay: Infinity
    });

    let score = 0;

    rl.on('line', (line) => {
      const combo = line.replace(" ", "");
      score += outcome[combo];
    });

    await new Promise((res) => rl.once('close', res));

    expect(score).toBe(15);
  });

  test('part 01', async function () {

    const rl = readline.createInterface({
      input: fs.createReadStream('test/resources/day02'),
      crlfDelay: Infinity
    });

    let score = 0;
    rl.on('line', (line) => {
      const combo = line.replace(" ", "");
      score += outcome[combo];
    });

    await new Promise((res) => rl.once('close', res));

    console.log(score);
  });
});

describe("day 02, part 02 – need to lose", () => {
  // A - rock
  // B - paper
  // C - scissors
  // X - rock (1p)
  // Y - paper (2p)
  // Z - scissors (3p)
  // wins (6p)
  // AZ = 2 + 6 = 8
  // BZ = 3 + 6 = 9
  // CZ = 1 + 6 = 7
  // draws (3p)
  // AY = 1 + 3 = 4
  // BY = 2 + 3 = 5
  // CY = 3 + 3 = 6
  // losses (0p)
  // AX = 3 + 0 = 3
  // BX = 1 + 0 = 1
  // CX = 2 + 0 = 2
  const outcome: {[key: string] : number} = {
    AX: 3,
    BX: 1,
    CX: 2,
    AY: 4,
    BY: 5,
    CY: 6,
    AZ: 8,
    BZ: 9,
    CZ: 7,
  };

  test('sample', async function () {

    const rl = readline.createInterface({
      input: fs.createReadStream('test/resources/day02.sample'),
      crlfDelay: Infinity
    });


    let result = 0;
    rl.on('line', (line) => {
      const combo = line.replace(" ", "");
      result += outcome[combo];
    });

    await new Promise((res) => rl.once('close', res));

    expect(result).toBe(12);
  });

  test('part 02', async function () {
    const rl = readline.createInterface({
      input: fs.createReadStream('test/resources/day02'),
      crlfDelay: Infinity
    });

    let result = 0;
    rl.on('line', (line) => {
      const combo = line.replace(" ", "");
      result += outcome[combo];
    });

    await new Promise((res) => rl.once('close', res));

    console.log(result);
  });

});