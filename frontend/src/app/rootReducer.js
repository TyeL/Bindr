import { combineReducers } from "redux";
import bindrsReducer from '../bindrs/BindrsReducer';
import snippetsReducer from "../snippets/SnippetsReducer";
import newBindrReducer from '../bindrs/NewBindrReducer';
import editBindrReducer from '../bindrs/EditBindrReducer';
import deleteBindrReducer from "../bindrs/DeleteBindrReducer";


const rootReducer = combineReducers({
    bindrs: bindrsReducer,
    snippets: snippetsReducer,
    newBindr: newBindrReducer,
    editBindr: editBindrReducer,
    deleteBindr: deleteBindrReducer,
});

export default rootReducer;