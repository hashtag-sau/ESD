import React, { useEffect, useState } from 'react';

const Practice = () => {

    const [data, setData] = useState([
        {
            "title": "sunt aut facere repellat provident occaecati excepturi optio reprehenderit"
        }]);

    useEffect(() => {
        fetch('https://jsonplaceholder.typicode.com/posts')
            .then(response => response.json())
            .then(json => setData(json));

    }, []);


    return (
        <div>
            <ul>
                {data.map((item, index) => {

                    return (
                        <li>
                            {item.title}
                        </li>
                    )

                })}
            </ul>
        </div>
    );
};

export default Practice;