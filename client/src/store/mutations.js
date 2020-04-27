const addCounter = (state,payload) => {
  if(payload.value !== undefined) {
    return state.counter =payload.value;
  }
  return state.counter++;
};

const subCounter = (state) => {
  return state.counter--;
};

export default { addCounter, subCounter };
