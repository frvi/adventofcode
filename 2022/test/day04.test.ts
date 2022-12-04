import {getInput} from "../src/helpers";

function partialOverlap(sections: Array<Array<number>>) : boolean {
  const [sections1, sections2] = sections;
  return sections1.filter((item) => sections2.includes(item)).length > 0;
}

function completeOverlap(sections: Array<Array<number>>) : boolean {
  const [sections1, sections2] = sections;
  return sections1.filter((item) => sections2.includes(item)).length === sections1.length ||
    sections2.filter((item) => sections1.includes(item)).length === sections2.length;
}


function getRanges(elves: Array<string>) : Array<Array<number>> {
  const [elf1, elf2] = elves;
  return [getRange(elf1), getRange(elf2)];
}

function getRange(sections: string): Array<number> {
  const [start, stop] = sections.match(/(\d+)-(\d+)/)?.slice(1) as string[];
  return Array.from({length: parseInt(stop, 10) - parseInt(start, 10) + 1}, (_, i) => i + parseInt(start, 10));
}

describe('day 04, part 01', function () {
  test('sample', async function () {
    const input = await getInput('test/resources/day04.sample');

    const result = input
      .map((pair) => pair.split(','))
      .map(getRanges)
      .filter(completeOverlap)
      .reduce((acc, curr) => acc + (curr ? 1 : 0), 0);

    expect(result).toBe(2);
  });

  test('part 01', async function () {
    const input = await getInput('test/resources/day04');

    const result = input
      .map((pair) => pair.split(','))
      .map(getRanges)
      .map(completeOverlap)
      .reduce((acc, curr) => acc + (curr ? 1 : 0), 0);

    console.log(`part 1: ${result}`);

    expect(result).toBe(441);
  });
});

describe('day 04, part 02', function () {
  test('sample', async function () {
    const input = await getInput('test/resources/day04.sample');

    const result = input
      .map((pair) => pair.split(','))
      .map(getRanges)
      .map(partialOverlap)
      .reduce((acc, curr) => acc + (curr ? 1 : 0), 0);

    expect(result).toBe(4);
  });

  test('part 02', async function () {
    const input = await getInput('test/resources/day04');

    const result = input
      .map((pair) => pair.split(','))
      .map(getRanges)
      .map(partialOverlap)
      .reduce((acc, curr) => acc + (curr ? 1 : 0), 0);

    console.log(`part 2: ${result}`);

    expect(result).toBe(861);
  });
});
