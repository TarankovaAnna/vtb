import http from 'k6/http';
import { Counter } from 'k6/metrics';

const CounterErrors = new Counter('Errors');


export const options = {
    discardResponseBodies: true,

    scenarios: {
        contacts: {
            executor: 'ramping-arrival-rate',

            // Start iterations per `timeUnit`
            startRate: 30,

            // Start `startRate` iterations per minute
            timeUnit: '1m',

            // Pre-allocate necessary VUs.
            preAllocatedVUs: 50,

            stages: [
                // Start 30 iterations per `timeUnit` for the first minute.
                { target: 30, duration: '5m' },

                // Linearly ramp-up to starting 60 iterations per `timeUnit` over the following two minutes.
                { target: 60, duration: '10s' },

                // Continue starting 600 iterations per `timeUnit` for the following four minutes.
                { target: 60, duration: '5m' },

                // Linearly ramp-down to starting 60 iterations per `timeUnit` over the last two minutes.
                { target: 90, duration: '10s' },

                // Linearly ramp-down to starting 60 iterations per `timeUnit` over the last two minutes.
                { target: 90, duration: '5m' },

                // Linearly ramp-down to starting 60 iterations per `timeUnit` over the last two minutes.
                { target: 120, duration: '10s' },

                // Linearly ramp-down to starting 60 iterations per `timeUnit` over the last two minutes.
                { target: 120, duration: '5m' },

                // Linearly ramp-down to starting 60 iterations per `timeUnit` over the last two minutes.
                { target: 150, duration: '10s' },

                // Linearly ramp-down to starting 60 iterations per `timeUnit` over the last two minutes.
                { target: 150, duration: '5m' },

            ],
        },
    },
};

export default function () {
    var randomNumber = Math.floor(Math.random() * 100000000);
    let data = { "msg_id": `${randomNumber}`};
    const resp = http.post('http://localhost:8080/post_message',JSON.stringify(data),{ headers: { 'Content-Type': 'application/json' } });

    if (resp.status !== 200) {
        CounterErrors.add(1);
    }

}