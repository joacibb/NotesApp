import React, {useEffect, useState} from 'react';
import CreateNotePopup from '../modals/CreateNotePopup'
import Card from './Card';
import {useNavigate} from 'react-router-dom'

const NotesApp = () => {
    const [modal, setModal] = useState(false)
    const [taskList, setTaskList] = useState([])
    const [taskListArchived, setTaskListArchived] = useState([])
    const [generalCategoryList, setGeneralCategoryList] = useState([])
    const [isArchived, setIsArchived] = useState(false);
    const [categorySelected, setCategorySelected] = useState('');
    const [allCategorySelected, setAllCategorySelected] = useState(true);
    const navigate = useNavigate();
    
    useEffect(() => {
        let arr = localStorage.getItem("taskList")
       
        if(arr){
            let obj = JSON.parse(arr)
            setTaskList(obj)
        }
    }, [])


    const deleteTask = (index) => {
        let tempList = taskList
        tempList.splice(index, 1)
        localStorage.setItem("taskList", JSON.stringify(tempList))
        setTaskList(tempList)
        navigate('/');
    }

    const deleteTaskArchived = (index) => {
        let tempList = taskListArchived
        tempList.splice(index, 1)
        localStorage.setItem("taskListArchived", JSON.stringify(tempList))
        setTaskListArchived(tempList)
        navigate('/');
    }

    const archiveNote = (taskObj, index) => {
        let tempList = taskListArchived
        deleteTask(index);
        tempList.push(taskObj)
        localStorage.setItem("taskListArchived", JSON.stringify(tempList))
        setTaskListArchived(tempList)
        navigate('/');
    }

    const unarchiveNote = (taskObj, index) => {
        let tempList = taskList
        deleteTaskArchived(index)
        tempList.push(taskObj)
        localStorage.setItem("taskList", JSON.stringify(tempList))
        setTaskList(tempList)
        navigate('/'); 
    }

    
    const updateListArray = (obj, index) => {
        let tempList = taskList
        tempList[index] = obj
        localStorage.setItem("taskList", JSON.stringify(tempList))
        setTaskList(tempList)
        filterRepeatedCategories(obj.Categories)
        navigate('/');
    }

    const updateArchivedListArray = (obj, index) => {
        let tempList = taskListArchived
        tempList[index] = obj
        localStorage.setItem("taskListArchived", JSON.stringify(tempList))
        setTaskListArchived(tempList)
        filterRepeatedCategories(obj.Categories)
        navigate('/');
    }
    
    const toggle = () => {
        setModal(!modal);
    }

    const saveTask = (taskObj) => {
        let tempList = taskList
        tempList.push(taskObj)
        localStorage.setItem("taskList", JSON.stringify(tempList))
        setTaskList(taskList)
        filterRepeatedCategories(taskObj.Categories)
        setModal(false)
    }

    const getNotes = () => {
        if(allCategorySelected){
            return taskList.map((obj , index) => <Card taskObj = {obj} index = {index} deleteTask = {deleteTask} updateListArray = {updateListArray} archiveNote = {archiveNote}/> )
        }else{
            return taskList.filter((note) => note.Categories.includes(categorySelected)).map((obj , index) => <Card taskObj = {obj} index = {index} deleteTask = {deleteTask} updateListArray = {updateListArray} archiveNote = {archiveNote}/> )
        }
        
    }

    const getArchivedNotes = () =>{
       return (taskListArchived.map((obj , index) =>
        <Card taskObj = {obj} index = {index} deleteTask = {deleteTaskArchived} updateListArray = {updateArchivedListArray} archiveNote = {unarchiveNote}/> 
        ));
    }

    const filterRepeatedCategories = (categoryList) =>{
        const notRepeatedCategories = categoryList.filter(cat => !generalCategoryList.includes(cat));
        setGeneralCategoryList(generalCategoryList.concat(notRepeatedCategories))
    }

    const handleSelectorChange = (event) =>{
        const category = event.target.value
        if(category !== "allCategories"){
            setCategorySelected(category)
            setAllCategorySelected(false)
        }
        else{
            setAllCategorySelected(true)
            setCategorySelected(category)
        }
    }

    return (
        !isArchived
            ? <>
                <div className = "header text-center">
                    <h3 style={{display: "flex", margin: "35px"}}>My Notes</h3>
                    <div className='header-buttons'>
                        <button className = "btn btn-primary mt-2" onClick = {() => setModal(true)} >Create Note</button>
                        <button className = "btn btn-secondary mt-2" onClick = {() => setIsArchived(!isArchived)} >Archived</button>
                        <select
                            name="category-list"
                            id="category-list"
                            onChange={(category) => handleSelectorChange(category)}
                        >
                            <option value="allCategories">All</option>
                            {generalCategoryList.map((category) => <option value={category}>{category}</option>)}
                        </select>
                    </div>
                </div>
                <div className = "task-container">
                {getNotes()}
                </div>
                <CreateNotePopup toggle = {toggle} modal = {modal} save = {saveTask}/>
             </>
            :<>
                <div className = "header text-center">
                <h3 style={{display: "flex", margin: "35px"}}>Archived Notes</h3>
                <div className='header-buttons'>
                    <button className = "btn btn-secondary mt-2" onClick = {() => setIsArchived(!isArchived)} >Unarchived</button>
                </div>
                </div>
                <div className = "task-container">
                {taskListArchived && getArchivedNotes()}
                </div>
                <CreateNotePopup toggle = {toggle} modal = {modal} save = {saveTask}/>
            </>
        
        
    );
};

export default NotesApp;