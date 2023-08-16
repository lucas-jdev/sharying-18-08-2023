import { render } from '@testing-library/react'
import Card from '../components/card';
import { Status } from '../models/Task';

describe('IndexTask Component', () => {
    it('deve renderizar o título', () => {
        const { getByText } = render(<Card task={{
            id: "code",
            title: "Tarefas",
            description: "Tarefas do dia a dia",
            status: Status.NOT_STARTED
        }} />)

        expect(getByText('Tarefas')).toBeInTheDocument()
    })
})