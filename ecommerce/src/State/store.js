import { legacy_createStore,applyMiddleware,combineReducers } from "redux";
import thunk from "redux-thunk";
import adminOrderReducer from "./Admin/Order/Reducer";
import { authReducer } from "./Auth/Reducer";
import { cartReducer } from "./Cart/Reducer";
import { orderReducer } from "./Order/Reducer";
import { customerProductReducer } from "./Product/Reducer";


 const rootReducers=combineReducers({
    auth:authReducer,
    products:customerProductReducer,
    cart:cartReducer,
    order:orderReducer,
    adminOrder:adminOrderReducer
})
export const store = legacy_createStore(rootReducers,applyMiddleware(thunk))