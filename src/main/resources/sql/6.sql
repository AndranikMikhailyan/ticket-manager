select p.birth_day
from tickets t
inner join passengers p on t.passenger_id = p.id
group by p.birth_day
having count(p.birth_day) > 5;