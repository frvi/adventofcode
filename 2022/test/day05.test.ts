import {getInput} from "../src/helpers";

function buildInstructions(line: string) : Array<number> {
  const strings = line.match(/move (?<move>\d+) from (?<from>\d+) to (?<to>\d+)/);
  const {move, from, to} = strings?.groups || {};
  return move && from && to ? [Number(move), Number(from), Number(to)] : [];
}

function toCrates(line: string) : Array<string> {
  return line.match(/([A-Z])| {4}/g) || [];
}

describe('day 05, part 01', function () {
  test('sample', async function () {
    const input = await getInput('test/resources/day05.sample');
    const cutoff = input.indexOf('');
    const stacks: Array<Array<string>> = [];

    input
      .slice(0, cutoff - 1)
      .map(toCrates)
      .map((crates) => crates.forEach((crate, index) => {
        if (crate.trim() !== '') {
          stacks[index] = stacks[index] || [];
          stacks[index].push(crate);
        }
      }));

    const instructions = input
      .slice(cutoff + 1)
      .map(buildInstructions);

    instructions.forEach(([move, from, to]) => {
      for (let i = 0; i < move; i++) {
        const moving = stacks[from - 1].shift();
        stacks[to - 1].unshift(moving!);
      }
    });

    const result = stacks.map((stack) => stack.shift()).join('');

    expect(result).toEqual('CMZ');

  });

  test('part 01', async function () {
    const input = await getInput('test/resources/day05');
    const cutoff = input.indexOf('');
    const stacks: Array<Array<string>> = [];
    input
      .slice(0, cutoff - 1)
      .map(toCrates)
      .map((crates) => crates.forEach((crate, index) => {
        if (crate.trim() !== '') {
          stacks[index] = stacks[index] || [];
          stacks[index].push(crate);
        }
      }));

    const instructions = input
      .slice(cutoff + 1)
      .map(buildInstructions);

    instructions.forEach(([move, from, to]) => {
      for (let i = 0; i < move; i++) {
        const moving = stacks[from - 1].shift();
        stacks[to - 1].unshift(moving!);
      }
    });

    const result = stacks.map((stack) => stack.shift()).join('');
    expect(result).toEqual('BSDMQFLSP');
  });
});


describe('day 05, part 02', function () {
  test('sample', async function () {
    const input = await getInput('test/resources/day05.sample');
    const cutoff = input.indexOf('');
    const stacks: Array<Array<string>> = [];

    input
      .slice(0, cutoff - 1)
      .map(toCrates)
      .map((crates) => crates.forEach((crate, index) => {
        if (crate.trim() !== '') {
          stacks[index] = stacks[index] || [];
          stacks[index].push(crate);
        }
      }));

    const instructions = input
      .slice(cutoff + 1)
      .map(buildInstructions);

    instructions.forEach(([move, from, to]) => {
      const crateMover : Array<string> = [];
      for (let i = 0; i < move; i++) {
        const moving = stacks[from - 1].shift();
        crateMover.push(moving!);
      }
      stacks[to - 1].unshift(...crateMover);
    });

    const result = stacks.map((stack) => stack.shift()).join('');

    expect(result).toEqual('MCD');
  });

  test('part 01', async function () {
    const input = await getInput('test/resources/day05');
    const cutoff = input.indexOf('');
    const stacks: Array<Array<string>> = [];
    input
      .slice(0, cutoff - 1)
      .map(toCrates)
      .map((crates) => crates.forEach((crate, index) => {
        if (crate.trim() !== '') {
          stacks[index] = stacks[index] || [];
          stacks[index].push(crate);
        }
      }));

    const instructions = input
      .slice(cutoff + 1)
      .map(buildInstructions);

    instructions.forEach(([move, from, to]) => {
      const crateMover : Array<string> = [];
      for (let i = 0; i < move; i++) {
        const moving = stacks[from - 1].shift();
        crateMover.push(moving!);
      }
      stacks[to - 1].unshift(...crateMover);
    });

    const result = stacks.map((stack) => stack.shift()).join('');

    expect(result).toEqual('PGSQBFLDP');
  });
});