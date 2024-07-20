import { Button } from '@mui/material';
import React from 'react';


function NotFoundPage() {
    return (
        <>
            <h1> 404 Not Found </h1>
            <p>The page you are looking for doesn't exist.</p>
            <Button variant="contained" color="secondary" href="/"> Visit Home </Button>
        </>
    );
}

export default NotFoundPage;