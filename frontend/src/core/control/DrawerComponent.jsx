import {Divider, IconButton, List, ListItem, ListItemButton, ListItemIcon, ListItemText, styled} from "@mui/material";
import ChevronLeftIcon from "@mui/icons-material/ChevronLeft";
import DashboardCustomizeIcon from "@mui/icons-material/DashboardCustomize";
import Drawer from "@mui/material/Drawer";
import * as React from "react";
import DashboardIcon from '@mui/icons-material/Dashboard';
import { useNavigate } from "react-router-dom";
import PersonSearchIcon from '@mui/icons-material/PersonSearch';
import BugReportIcon from '@mui/icons-material/BugReport';
import ScienceIcon from '@mui/icons-material/Science';



const DrawerHeader = styled('div')(({ theme }) => ({
    display: 'flex',
    alignItems: 'center',
    justifyContent: 'flex-end',
    padding: theme.spacing(0, 1),
    backgroundColor: theme.palette.secondary.main,
    // necessary for content to be below app bar
    ...theme.mixins.toolbar,
}));

export default function DrawerComponent( {open, toggleDrawer})
{
    const drawerWidth = '23%';
    const navigate = useNavigate();

    return (
        <Drawer onClose={toggleDrawer}
                sx={{
                    width: drawerWidth,
                    flexShrink: 0,
                    '& .MuiDrawer-paper': {
                        width: drawerWidth,
                        boxSizing: 'border-box',
                    },}} variant="temporary" anchor="left" open={open}>

        <DrawerHeader>
            <IconButton color="primary" onClick={toggleDrawer}>
                <ChevronLeftIcon />
            </IconButton>
        </DrawerHeader>

        <List>
            <ListItem key={'Dashboard'} disablePadding onClick={() => navigate('/')}>
                <ListItemButton>
                    <ListItemIcon>
                        <DashboardIcon color="primary"/>
                    </ListItemIcon>
                    <ListItemText primary={'Dashboard'} secondary={'Dev Center'} />
                </ListItemButton>
            </ListItem>
            <ListItem key={'Query Search'} disablePadding onClick={() => navigate('/home')}>
                <ListItemButton>
                    <ListItemIcon>
                        <PersonSearchIcon color="primary"/>
                    </ListItemIcon>
                    <ListItemText primary={'Query'} secondary={'Query Your Org'} />
                </ListItemButton>
            </ListItem>
            <ListItem key={'Query Search'} disablePadding onClick={() => navigate('/case')}>
                <ListItemButton>
                    <ListItemIcon>
                        <BugReportIcon color="primary" />
                    </ListItemIcon>
                    <ListItemText primary={'Case'} secondary={'Send Case'} />
                </ListItemButton>
            </ListItem>
            <ListItem key={'Meta Center'} disablePadding onClick={() => navigate('/meta')}>
                <ListItemButton>
                    <ListItemIcon>
                        <ScienceIcon color="primary" />
                    </ListItemIcon>
                    <ListItemText primary={'Meta Data'} secondary={'Explore the meta data of your org'} />
                </ListItemButton>
            </ListItem>
        </List>
    </Drawer>
    );
}