
import { Box, CssBaseline, Drawer, List, ListItem, ListItemButton, ListItemIcon, ListItemText, Toolbar, useMediaQuery, useTheme } from '@mui/material';
import React, { useState } from 'react'
import { Route,Routes, useNavigate } from 'react-router-dom';
import EmailIcon from '@mui/icons-material/Email';
import InboxIcon from '@mui/icons-material/Inbox';
import DashboardIcon from '@mui/icons-material/Dashboard';
import PersonIcon from '@mui/icons-material/Person';
import ShoppingBagIcon from '@mui/icons-material/ShoppingBag';
import AddCircleIcon from '@mui/icons-material/AddCircle';
import SellIcon from '@mui/icons-material/Sell';
import AccountBoxIcon from '@mui/icons-material/AccountBox';

import Dashboard from './Dashboard';
import CreateProductForm from './CreateProductForm';
import ProductsTable from './ProductsTable';
import OrdersTable from './OrdersTable';
import CustomersTable from './CustomersTable';
import AdminDashboard from './Dashboard';

const menu=[
    {name:"Dashboard",path:"/admin",icon:<DashboardIcon/>},
    {name:"Products",path:"/admin/products",icon:<SellIcon/>},
    {name:"Customers",path:"/admin/customers",icon:<PersonIcon/>},
    {name:"Orders",path:"/admin/orders",icon:<ShoppingBagIcon/>},
    {name:"AddProduct",path:"/admin/product/create",icon:<AddCircleIcon/>}
]

const Admin = () => {
    const theme=useTheme();
    const isLargeScreen=useMediaQuery(theme.breakpoints.up("lg"));
    const [sideBarVisible,setSideBarVisible]=useState(false);
    const navigate=useNavigate();

    const drawer=(
        <Box
        sx={{
            overflow:"auto",
            display:"flex",
            flexDirection:"column",
            justifyContent:"space-between",
           // border:"1px solid blue",
            height:"100%"
        }}
        >
            {isLargeScreen  && <Toolbar/>}
            <List>
                {menu.map((item,index)=><ListItem key={item.name} disablePadding onClick={()=>navigate(item.path)}>
                    <ListItemButton>
                        <ListItemIcon>
                           {item.icon}
                        </ListItemIcon>
                        <ListItemText>{item.name}</ListItemText>
                    </ListItemButton>
                </ListItem>)}
            </List>
            <List>
                <ListItem disablePadding >
                    <ListItemButton>
                        <ListItemIcon>
                           <AccountBoxIcon/>

                        </ListItemIcon>
                        <ListItemText>Account</ListItemText>
                    </ListItemButton>
                </ListItem>
            </List>
        </Box>
    )
  return (
    <div>
        <div className='flex h-[100vh] '>

            <CssBaseline/>
            <div className=' shadow-lg shadow-gray-600 w-[15%] border border-r-gray-300 h-full sticky top-0'>
            {drawer}
            </div>
            
         
          <div className='w-[85%]'>
            <Routes>
                <Route path='/'element={<AdminDashboard/>}></Route>
                <Route path='/product/create'element={<CreateProductForm/>}></Route>
                <Route path='/products'element={<ProductsTable/>}></Route>
                <Route path='/orders'element={<OrdersTable/>}></Route>
                <Route path='/customers'element={<CustomersTable/>}></Route>
            </Routes>
          </div>
          </div>
        
    </div>
  )
}

export default Admin
