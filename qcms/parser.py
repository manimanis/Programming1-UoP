import os
import pprint
from bs4 import BeautifulSoup

def getText(txt: str):
    return " ".join(txt.strip().split())

folder = "cs1102"
curfolder = os.path.dirname(__file__)

html_files = {}
folder_path = os.path.join(curfolder, folder)
for file in os.listdir(folder_path):
    path = os.path.join(folder_path, file)
    if os.path.isdir(path):
        html_files[file] = []
        for afile in os.listdir(path):
            filepath = os.path.join(path, afile)
            if afile.endswith(".html"):
                html_files[file].append(filepath)
print(html_files)

for unit in html_files.keys():
    print(unit)
    unit_questions = []
    for file in html_files[unit]:
        with open(file, "r") as f:
            text = f.read()
        bs = BeautifulSoup(text, "lxml")
        qlist = []
        questions = bs.find_all(class_="formulation clearfix")
        for question in questions:
            qdict = {
                "question": "",
                "answers": []
            }
            text = question.find(class_="qtext")
            pars = text.find_all("p")
            qtext = ""
            for par in pars:
                if qtext != "":
                    qtext += "\n"
                qtext += getText(par.text)
            answers = question.select(".answer > div")
            qdict["question"] = qtext
            for answer in answers:
                an = getText(answer.find(class_="answernumber").text)
                an_text = getText(answer.find(class_="flex-fill").text)
                qdict["answers"].append([an, an_text])
            qlist.append(qdict)
        corrects = bs.find_all(class_="rightanswer")
        
        for idx, correct in enumerate(corrects):
            rightanswer = getText(correct.text)[len("The correct answer is:"):].strip()
            valid = False
            for idx2, options in enumerate(qlist[idx]["answers"]):
                corrans = options[1] == rightanswer
                if corrans:
                    valid = True
                options.append(corrans)
            if not valid:
                print(f"No valid answer question {unit} question {idx+1} file {os.path.basename(file)}")
    
        for question in qlist:
            if question not in unit_questions:
                unit_questions.append(question)
    print(len(unit_questions))
    # pprint.pprint(unit_questions)
    