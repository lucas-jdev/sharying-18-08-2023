import { Status } from "../models/Task";

export const deleteTask = async (id: string) => {
    await fetch(`https://taskin-backend-production.up.railway.app/api/task/${id}`, {
        method: 'DELETE'
    });
};

export const archiveTask = async (id: string) => {
    await fetch(`https://taskin-backend-production.up.railway.app/api/task/${id}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({
            status: Status.ARCHIVED
        }),
    });
};