import React , {useEffect, useState} from "react";
import {Box, IconButton} from "@mui/material";
import MessageIcon from '@mui/icons-material/Message';
import CardGiftcardIcon from '@mui/icons-material/CardGiftcard';
import {useNavigate} from "react-router-dom";
import ArrowCircleUpIcon from '@mui/icons-material/ArrowCircleUp';
import { useTheme } from "@emotion/react";
import { blue } from "@mui/material/colors";
import MenuIcon from '@mui/icons-material/Menu';

export default function BottomComponent({toggleDrawer}) {
    const [lastScrollTop, setLastScrollTop] = useState(0);
    const [showController, setShowController] = useState(true);
    const navigate = useNavigate();
    const theme = useTheme();
    
    const scrollToTop = () => {
        window.scrollTo({
            top: 0,
            behavior: 'smooth',
        });
    };

    useEffect(() => {
        const handleScroll = () => {
            const currentScrollTop =  document.documentElement.scrollTop;
            if (currentScrollTop < lastScrollTop) {
                // Scrolling Up
                setShowController(true);
            } else {
                // Scrolling Down
                setShowController(false);
            }
            setLastScrollTop(currentScrollTop <= 0 ? 0 : currentScrollTop);
        };

        window.addEventListener('scroll', handleScroll);
        return () => window.removeEventListener('scroll', handleScroll);
    }, [lastScrollTop]);

    return (
<div>
    {showController && (
        <Box elevation={10}
             sx={{ 
                position: 'fixed',
                bottom: 0,
                left: 0,
                width: '100%',
                height : "5vh",
                backgroundColor: theme.palette.secondary.main,
                display: 'flex',
                justifyContent: 'space-around',
                zIndex: 1000,
        }}>

            <IconButton color="primary" onClick={() => toggleDrawer()}>
                <MenuIcon/>
            </IconButton>

            <IconButton color="primary" onClick={() => scrollToTop()}>
                <ArrowCircleUpIcon/>
            </IconButton>
        </Box>
    )}
</div>
    );
}