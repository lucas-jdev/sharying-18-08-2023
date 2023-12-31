import { TaskProps } from "@/models/Task";
import { GetServerSideProps } from "next";
import { Card } from "@/components/card";
import Link from "next/link";

export default function IndexTask({ tasks }: HomeProps) {

    return (
        <>
            <h1 className="container mt-2">
                Tasks |{" "}
                <Link
                    href={"/task/form"}
                    className="btn btn-success"
                >
                    Nova Task
                </Link>
            </h1>
            <section className="container d-flex justify-content-center">
                {tasks.length === 0 ? (
                    <h2 className="title mt-3">
                        Você não tem tasks :(
                    </h2>
                    ) : 
                    <div className="d-flex flex-wrap">
                            {tasks.map(task => (        
                                <div key={task.id} className="w-33 p-2">
                                    <Card style={{maxWidth: '340px'}} task={task} />       
                                </div>
                            ))}
                    </div>
                    }
            </section>    
        </>
    );
}

export const getServerSideProps: GetServerSideProps = async () => {
    const response = await fetch('https://taskin-backend-production.up.railway.app/api/task');
    const data = await response.json();
    
    return {
        props: {
            tasks: data
        }
    };
};

interface HomeProps {
    tasks: TaskProps[];
}
