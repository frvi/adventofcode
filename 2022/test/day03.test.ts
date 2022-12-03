import {getInput} from "../src/helpers";

function getScore(char: string): number {
  return char === char.toLowerCase() ? char.charCodeAt(0) - 'a'.charCodeAt(0) + 1 : char.charCodeAt(0) - 'A'.charCodeAt(0) + 27;
}

function splitIntoCompartments(line: string) : Array<string> {
  const middle = line.length / 2;
  return [line.slice(0, middle), line.slice(middle)];
}

function findCommonItem(containers: Array<string>) : string {
  const [common] = containers
    .map((items) => new Set(items))
    .reduce((items1, items2) => new Set([...items1].filter((item) => items2.has(item))));
  return common;
}

function* groupRucksacks(input: Array<string>, size: number) : Generator<Array<string>> {
  for (let i = 0; i+size <= input.length; i+=size) {
    yield input.slice(i, i+size);
  }
}

describe('day 03, part 01', function () {
  test('sample', async function () {
    const input = await getInput('test/resources/day03.sample');

    const score = input
      .map(splitIntoCompartments)
      .map(findCommonItem)
      .reduce((acc, commonItem) => acc + getScore(commonItem), 0);

    expect(score).toBe(157);
  });

  test('part 01', async function () {
    const input = await getInput('test/resources/day03');

    const score = input
      .map(splitIntoCompartments)
      .map(findCommonItem)
      .reduce((acc, commonItem) => acc + getScore(commonItem), 0);

    expect(score).toBe(7742);
  });
});

describe('day 03, part 02', function () {
  test('sample', async function () {
    const input = await getInput('test/resources/day03.sample');

    const result = Array
      .from(groupRucksacks(input, 3))
      .map(findCommonItem)
      .reduce((acc, commonItem) => acc + getScore(commonItem), 0);

    expect(result).toBe(70);
  });

  test('part 02', async function () {
    const input = await getInput('test/resources/day03');

    const result = Array
      .from(groupRucksacks(input, 3))
      .map(findCommonItem)
      .reduce((acc, commonItem) => acc + getScore(commonItem), 0);

    expect(result).toBe(2276);

  });
});
