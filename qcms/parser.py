import json
import os
import pprint
from bs4 import BeautifulSoup


def getText(txt: str):
    return " ".join(txt.strip().split())


def extract_paragraphs(elem):
    pars = elem.find_all("p")
    partext = ""
    for par in pars:
        if partext:
            partext += "\n"
        partext += getText(str(par.prettify()))
    if partext == "":
        partext = getText(str(elem.prettify()))
    return partext


folder = "math1201"
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

all_questions = []
for unit in html_files.keys():
    unit_questions = []
    for file in html_files[unit]:
        with open(file, "r", encoding="utf8") as f:
            text = f.read()
        bs = BeautifulSoup(text, "lxml")
        qlist = []
        questions = bs.find_all(class_="formulation clearfix")
        for idx, question in enumerate(questions):
            qdict = {"question": "", "answers": []}
            text = extract_paragraphs(question.find(class_="qtext"))
            qdict["question"] = text
            if text == "":
                print(
                    f"No question {unit} q:{idx+1} file:{os.path.basename(file)}"
                )

            answers = question.select(".answer > div")
            for answer in answers:
                an = getText(answer.find(class_="answernumber").text)
                an_text = getText(answer.find(class_="flex-fill").text)
                qdict["answers"].append([an, an_text])
            qlist.append(qdict)
        
        corrects = bs.find_all(class_="rightanswer")
        if len(corrects) != len(qlist):
            print(f"unsufficient answers {unit} f.{os.path.basename(file)}")
        for idx, correct in enumerate(corrects):
            rightanswer = getText(correct.text)[len("The correct answer is:") :].strip()
            valid = False
            for idx2, options in enumerate(qlist[idx]["answers"]):
                corrans = options[1] == rightanswer
                if corrans:
                    valid = True
                options.append(corrans)
            if not valid:
                print(
                    f"No valid answer {unit} q.{idx+1} f.{os.path.basename(file)}"
                )
                print(rightanswer)
                pprint.pprint(qlist[idx])

        for question in qlist:
            if question not in unit_questions:
                unit_questions.append(question)
    print(unit)
    print(len(unit_questions))
    for question in unit_questions:
        if question not in all_questions:
            all_questions.append(question)
print("Total questions:", len(all_questions))

jsonfile = os.path.join(folder_path, folder + ".json")
with open(jsonfile, "w") as outfile:
    outfile.write(json.dumps(all_questions))
