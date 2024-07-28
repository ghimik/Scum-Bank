import initialState from "../initialState"

const dataReducer = (state = initialState, action) => {
    switch (action.type) {
        case 'SET_USERNAME':
            return {
                ...state, 
                username: action.payload
            }
        case 'SET_SESSION_UUID':
            return {
                ...state, 
                sessionUUID: action.payload
            }
        default:
            return state
    }
}

export default dataReducer;