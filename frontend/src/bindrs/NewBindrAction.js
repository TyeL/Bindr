import axios from 'axios';

export const NEW_BINDR_SUCCESS = 'NEW_BINDR_SUCCESS';
export const NEW_BINDR_ERRORS = 'NEW_BINDR_ERRORS';

export const newBindrSuccess = (bindr) => ({
    type: NEW_BINDR_SUCCESS,
    payload: { bindr }
});


export const newBindrErrors = (newBindrErrors) => ({
    type: NEW_BINDR_ERRORS,
    payload: { newBindrErrors }
});

export function newBindr(item) {
    return (dispatch) => {
        return axios.post("http://localhost:8080/bindrs/", item)
            .then(res => dispatch(newBindrSuccess(res.data)))
            .catch(err => {
                dispatch(newBindrErrors(err.response.data))
            })
    }
}

