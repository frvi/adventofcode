import {PathLike} from "fs";
import * as readline from "readline";
import * as fs from "fs";

export async function getInput(path: PathLike): Promise<Array<string>> {
  const input: Array<string> = [];

  const rl = readline.createInterface({
    input: fs.createReadStream(path),
    crlfDelay: Infinity
  });

  rl.on('line', (line) => {
    input.push(line);
  });

  await new Promise((res) => rl.once('close', res));

  return input;
}

export function* windowGenerator(input: Array<string>, size: number) : Generator<Array<string>> {
  for (let i = 0; i+size < input.length; i++) {
    yield input.slice(i, i+size);
  }
}