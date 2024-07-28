import { createStore } from "redux";
import userdataReducer from './reducers/userdataReducer';

const store = createStore(userdataReducer);

export default store;
