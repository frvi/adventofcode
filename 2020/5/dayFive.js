'use strict';

const {readFile} = require('fs').promises;

const convertSeatToBinary = (seat) => {
    return seat
        .replace(/([FL])/g, '0')
        .replace(/[BR]/g, '1');
}

const getSeats = (seats) => {
    return seats.map(seat => getSeat(seat));
}

const getSeat = (seat) => {
    const binary = convertSeatToBinary(seat);
    const row = parseInt(binary.slice(0, 7), 2);
    const column = parseInt(binary.slice(7, 10), 2);
    const seatId = row * 8 + column;
    return {row, column, seatId}
}
const getHighestSeatId = (seats) => {
    const seatIds = getSeatIds(seats);
    return Math.max(...seatIds);
}

const getInput = async (filename) => {
    const input = await readFile(filename, 'utf8');
    return input.toString()
        .split('\n');
}

function getSeatIds(seats) {
    return seats.map(seat => seat.seatId);
}

const findMySeat = seats => {
    const seatIds = getSeatIds(seats);
    const first = Math.min(...seatIds);
    const last = getHighestSeatId(seats);
    const allSeatIds = Array(last - first + 1)
        .fill(0)
        .map((_, i) => first + i);
    return allSeatIds.find(seat => !seatIds.includes(seat));
}

module.exports = {
    convertSeatToBinary,
    getSeat,
    getSeats,
    getHighestSeatId,
    getInput,
    findMySeat
}