'use strict';

const {readFile} = require('fs').promises;

const convertSeatToBinary = (seat) => {
    return seat
        .replace(/([FL])/g, '0')
        .replace(/[BR]/g, '1');
}

const getSeat = (seat) => {
    const binary = convertSeatToBinary(seat);
    const row = parseInt(binary.slice(0, 7), 2);
    const column = parseInt(binary.slice(7, 10), 2);
    const seatId = row * 8 + column;
    return {row, column, seatId}
}

const getSeats = (seats) => {
    return seats.map(seat => getSeat(seat));
}

function sortSeatsOnSeatId(seats) {
    return [...seats].sort((a, b) => a.seatId - b.seatId);
}

const getHighestSeatId = (seats) => {
    return sortSeatsOnSeatId(seats).slice(-1)[0].seatId
}

const getInput = async (filename) => {
    const input = await readFile(filename, 'utf8');
    return input.toString()
        .split('\n');
}

const findMySeat = seats => {
    const sorted = sortSeatsOnSeatId(seats);
    const seatIds = sorted.map(seat => seat.seatId);
    const first = seatIds[0];
    const last = seatIds.slice(-1);
    const allSeatIds = Array(last - first + 1)
        .fill(0)
        .map((_, i) => first + i);
    return allSeatIds.filter(seat => !seatIds.includes(seat))[0];
}

module.exports = {
    convertSeatToBinary,
    getSeat,
    getSeats,
    getHighestSeatId,
    getInput,
    findMySeat
}