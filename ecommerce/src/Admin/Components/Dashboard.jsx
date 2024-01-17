import { Grid } from '@mui/material'
import React from 'react'
import ProductTableView from '../View/ProductTableView'
import Achievement from './Achievement'
import MonthlyOverview from './MonthlyOverview'
import OrdersTable from './OrdersTable'
import ProductsTable from './ProductsTable'

const AdminDashboard = () => {
  return (
    <div className='px-10'>
        <Grid container spacing={3}>
     <Grid item xs={12} md={4}>
      <div className='shadow-lg shadow-gray-600 ' >
      <Achievement/>
      </div>
</Grid>
     <Grid  item xs={12} md={8}>
      <div className='shadow-lg shadow-gray-600 '>
      <MonthlyOverview/>
      </div>
        
     </Grid>
     <Grid item xs={12} md={6}>
      <div className='shadow-lg shadow-gray-600 '>
      <OrdersTable/>
      </div>
      
     </Grid>
     <Grid item xs={12} md={6}>
      <div className='shadow-lg shadow-gray-600 '>
      <ProductTableView/>
      </div>
      
     </Grid>
     
        </Grid>
    </div>
  )
}

export default AdminDashboard
