import { ref } from 'vue'

export function useTest() {
    const test = ref('test')

    const set = async () => {
        
    }
    const setTest = (value) => {
        test.value = value
    }   

    return {
        test,
        setTest
    }
}
