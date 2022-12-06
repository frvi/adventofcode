import {getInput, windowGenerator} from "../src/helpers";

describe('day 06', function () {
  test.each([
    ["mjqjpqmgbljsphdztnvjfqwrcgsmlb", 4, 7],
    ["bvwbjplbgvbhsrlpgdmjqwftvncz", 4, 5],
    ["nppdvjthqldpwncqszvftbrmjlhg", 4, 6],
    ["nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg", 4, 10],
    ["zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw", 4, 11],
    ["mjqjpqmgbljsphdztnvjfqwrcgsmlb", 14, 19],
    ["bvwbjplbgvbhsrlpgdmjqwftvncz", 14, 23],
    ["nppdvjthqldpwncqszvftbrmjlhg", 14, 23],
    ["nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg", 14, 29],
    ["zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw", 14, 26]
  ])('sample %s with marker size %s finds %s', async function (input: string, size: number, expected: number) {
    const characters = input.split('');
    const generator = windowGenerator(characters, size);

    let marker = size;
    for (const window of generator) {
      if (new Set(window).size === size) {
        expect(marker).toEqual(expected);
        return;
      }
      marker++;
    }
  });

  test.each([[4, 1850], [14, 2823]])("marker size %s ", async function (size: number, expected: number) {
    const input = await getInput("test/resources/day06");
    const characters = input[0].split('');
    const generator = windowGenerator(characters, size);

    let marker = size;
    for (const window of generator) {
      if (new Set(window).size === size) {
        expect(marker).toEqual(expected);
        return;
      }
      marker++;
    }

    expect(true).toBeFalsy();
  });
});