import { applyMiddleware, createStore } from "redux";
import { composeWithDevTools } from "redux-devtools-extension";
import thunkMiddleware from 'redux-thunk';

import monitorReducer from './enhancers/monitorReducer';
import loggerMiddleware from './middleware/logger';
import rootReducer from "./rootReducer";

export default function configureStore(preloadedState) {
    const middlewares = [loggerMiddleware, thunkMiddleware];
    const middlewareEnhancer = applyMiddleware(...middlewares);

    const enhancers = [middlewareEnhancer, monitorReducer];
    const composedEnhancers = composeWithDevTools(...enhancers);

    return createStore(rootReducer, preloadedState, composedEnhancers);
};